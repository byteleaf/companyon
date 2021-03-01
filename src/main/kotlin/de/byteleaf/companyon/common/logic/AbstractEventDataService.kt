package de.byteleaf.companyon.common.logic

import de.byteleaf.companyon.auth.permission.PermissionHandler
import de.byteleaf.companyon.auth.permission.PermissionType
import de.byteleaf.companyon.common.dto.BaseDTO
import de.byteleaf.companyon.common.dto.BaseUpdateDTO
import de.byteleaf.companyon.common.dto.EntityUpdateType
import de.byteleaf.companyon.common.entity.BaseEntity
import de.byteleaf.companyon.common.event.EntityEvent
import de.byteleaf.companyon.common.util.GenericSupportUtil
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Emitter
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observables.ConnectableObservable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.event.EventListener
import org.springframework.data.mongodb.repository.MongoRepository

abstract class AbstractEventDataService<E : BaseEntity, O : BaseDTO, U : BaseUpdateDTO<O>, I, R : MongoRepository<E, String>> :
    AbstractDataService<E, O, I, R>() {

    private val POSITION_ENTITY_UPDATED_DTO = 2
    private var eventEmitter: Emitter<U>? = null
    private var eventPublisher: Flowable<U>
    @Autowired
    private lateinit var permissionHandler: PermissionHandler

    init {
        @Suppress("UNCHECKED_CAST")
        val observable: Observable<U> = Observable.create { em -> eventEmitter = em as Emitter<U> }
        val connectableObservable: ConnectableObservable<U> = observable.publish()
        connectableObservable.connect()
        eventPublisher = connectableObservable.toFlowable(BackpressureStrategy.MISSING)
    }

    fun getPublisher(vararg permissions: Pair<PermissionType, String?>): Flowable<U> {
        return eventPublisher.filter {
            permissionHandler.hasPermissions(permissions)
        }
    }

    @EventListener
    fun onEntityEvent(event: EntityEvent<*>) {
        if (event.entityType === getEntityType()) {
            val updatedEntity = GenericSupportUtil.createInstanceFromGeneric<U>(this, POSITION_ENTITY_UPDATED_DTO)
            @Suppress("UNCHECKED_CAST")
            updatedEntity.entity = event.entity as O
            updatedEntity.type = EntityUpdateType.getByEventType(event.eventType)
            eventEmitter!!.onNext(updatedEntity)
        }
    }
}
