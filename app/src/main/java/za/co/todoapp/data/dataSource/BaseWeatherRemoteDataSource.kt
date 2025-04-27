package za.co.todoapp.data.dataSource

import za.co.todoapp.data.remote.IKtorService

abstract class BaseWeatherRemoteDataSource(
    protected val ktorService: IKtorService = IKtorService.create()
)