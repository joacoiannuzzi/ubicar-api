package com.ubicar.ubicar.factories.property

import com.ubicar.ubicar.dtos.PropertyDTO
import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.factories.AbstractFactory
import com.ubicar.ubicar.factories.optionals.MaterialFactory
import com.ubicar.ubicar.factories.optionals.SecurityFactory
import org.springframework.stereotype.Component

@Component
class PropertyFactory(
  private val materialFactory: MaterialFactory,
  private val securityFactory: SecurityFactory,
  private val contactFactory: ContactFactory,
  private val openHouseDateFactory: OpenHouseDateFactory
) : AbstractFactory<Property, PropertyDTO> {

  override fun convert(input: Property): PropertyDTO {
    val materials = input.materials.map(materialFactory::convert).toMutableList()
    val security = input.security.map(securityFactory::convert).toMutableList()
    val contacts = input.contacts.map(contactFactory::convert).toMutableList()
    val openHouse = input.openHouse.map(openHouseDateFactory::convert).toMutableList()

    return PropertyDTO(
      input.id,
      input.title,
      input.price,
      input.condition,
      input.type,
      input.address,
      input.squareFoot,
      input.coveredSquareFoot,
      input.levels,
      input.constructionDate,
      input.style,
      input.environments,
      input.rooms,
      input.toilets,
      input.fullBaths,
      input.expenses,
      input.amenities,
      materials,
      security,
      input.parkDescription,
      input.links,
      contacts,
      openHouse,
      input.comments
    )
  }
}
