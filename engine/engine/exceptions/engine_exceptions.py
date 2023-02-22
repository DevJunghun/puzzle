from rest_framework.exceptions import APIException


class EngineException(APIException):
    path = '/'
    status_code = 500
    message = 'unknown exception.'
    default_code = 'unknown-exception'
