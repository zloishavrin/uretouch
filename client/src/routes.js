import { Home } from "./pages/HomePage/Home"
import { Login } from "./pages/LoginPage/Login"
import { Registration } from "./pages/RegistrationPage/Registration"
import { Start } from "./pages/StartPage/Start"

export const authRoutes = [
  {
      path: "/",
      element: <Home/>
  },
]

export const publicRoutes = [
  {
      path: '/start',
      element: <Start/>
  },
  {
      path: '/login',
      element: <Login/>
  },
  {
      path: '/registration',
      element: <Registration/>
  },
]