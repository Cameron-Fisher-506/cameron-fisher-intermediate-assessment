package za.co.todoapp.data.mappers

interface BaseDtoMapper<T, R> {
    fun toDomain(dto: T): R
    fun toDomain(dtoList: List<T>): List<R>
}