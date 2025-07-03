package za.co.todoapp.data.local

suspend infix fun <T> IBaseDao<T>.upsert(entity: T): T {
    val result = this.insert(entity)
    if (result == -1L) {
        this.update(entity)
    }
    return entity
}

suspend infix fun <T> IBaseDao<T>.upsert(entities: List<T>): List<T> {
    val results = this.insert(entities)
    val updateList: MutableList<T> = mutableListOf()

    for (i in results.indices) {
        if (results[i] == -1L) {
            updateList.add(entities[i])
        }
    }

    if (updateList.isNotEmpty()) {
        this.update(updateList)
    }

    return entities
}