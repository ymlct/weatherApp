package com.ymlct.weatherapp.domain.repository

sealed class Failure {
    object NetworkConnection : Failure()
    object ServerError : Failure()
    object DataBaseError : Failure()
    abstract class AbstractFailure : Failure()
}