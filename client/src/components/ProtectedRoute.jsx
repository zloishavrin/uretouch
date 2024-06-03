import { Navigate } from 'react-router-dom';

export const ProtectedRoute = ({ children, auth }) => {
  return auth ? children : <Navigate to="/" />;
};
