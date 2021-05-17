package com.ubicar.ubicar.services.address

import com.ubicar.ubicar.entities.Address

interface AddressService {

    fun findAll() : List<Address>

    fun save(address: Address) : Address

    fun findById(id: Long) : Address

    fun delete(address: Long)
}