package repository

import entity.ContactEntity

class ContactRepository {

    companion object {
        private val contacts = mutableListOf<ContactEntity>();

        fun save(contact: ContactEntity) {
            contacts.add(contact);
        }

        fun delete(contact: ContactEntity) {
            contacts.remove(contact)

//            var index = 0
//            for (item in contacts.withIndex()) {
//                if (item.value.name == contact.name && item.value.phone == contact.phone) {
//                    index = item.index
//                    break
//                }
//            }
//
//            contacts.removeAt(index)
        }

        fun getList(): List<ContactEntity> {
            return contacts
        }
    }
}