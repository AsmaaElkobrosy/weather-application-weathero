package com.example.wethero.localdatabase

import com.example.wethero.Model.Welcome

interface LocalSourceInterface {
        suspend fun insert(welcome: Welcome)
        suspend fun getAllStored():Welcome
//        suspend fun delete(welcome: Welcome)

}