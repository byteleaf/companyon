package de.byteleaf.companyon.common.control

import de.byteleaf.companyon.common.entity.BaseEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.security.access.prepost.PreAuthorize

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
abstract class AbstractAdminDataService<E : BaseEntity, O, I, R : MongoRepository<E, String>> : AbstractDataService<E, O, I, R>() {

    @PreAuthorize("hasAuthority('admin')")
    override fun create(input: I) = super.create(input)

    @PreAuthorize("hasAuthority('admin')")
    override fun update(id: String, input: I): O = super.update(id, input)

    @PreAuthorize("hasAuthority('admin')")
    override fun delete(id: String) = super.delete(id)

    @PreAuthorize("hasAuthority('admin')")
    override fun deleteAll() = super.deleteAll()
}
