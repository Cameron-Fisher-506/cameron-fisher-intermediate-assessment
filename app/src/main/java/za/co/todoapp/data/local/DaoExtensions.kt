package za.co.todoapp.data.local

suspend fun <T> IBaseDao<T>.upsert(entity: T, baseDao: IBaseDao<T>): T {
    val result = baseDao.insert(entity)
    if (result == -1L) {
        baseDao.update(entity)
    }
    return entity
}

suspend fun <T> IBaseDao<T>.upsert(entities: List<T>, baseDao: IBaseDao<T>): List<T> {
    val results = baseDao.insert(entities)
    val updateList: MutableList<T> = mutableListOf()

    for (i in results.indices) {
        if (results[i] == -1L) {
            updateList.add(entities[i])
        }
    }

    if (updateList.isNotEmpty()) {
        baseDao.update(updateList)
    }

    return entities
}