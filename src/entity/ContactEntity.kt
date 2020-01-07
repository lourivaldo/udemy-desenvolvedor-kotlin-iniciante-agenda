package entity

class ContactEntity(var name: String, var phone: String) {
    override fun equals(other: Any?): Boolean {

        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as ContactEntity

        if (other.name == name && other.phone == phone) {
            return true;
        }

        return super.equals(other)
    }
}