package de.byteleaf.companyon.common.logic

import de.byteleaf.companyon.common.repository.HistoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HistorizationService {

    @Autowired
    private lateinit var historyRepository: HistoryRepository


    fun checkForNewerVersions() {

    }

    fun updateVersions() {
        
    }
}