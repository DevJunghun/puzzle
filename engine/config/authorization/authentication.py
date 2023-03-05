from rest_framework.authentication import BaseAuthentication


class DefaultAuthentication(BaseAuthentication):
    def authenticate(self, request):
        print("pass")

    pass
