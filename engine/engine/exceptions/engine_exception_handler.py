from rest_framework.views import exception_handler

from engine.exceptions.engine_exceptions import EngineException


def engine_exception_handler(exc: EngineException, context: dict) -> [int]:
    response = exception_handler(exc, context)

    if response is not None and isinstance(exc, EngineException):
        response.data['status_code'] = response.status_code

        response.status_code = response.status_code
        response.data['message'] = exc.message
        response.data['path'] = exc.message

        response.data.pop('detail', None)

        return response
