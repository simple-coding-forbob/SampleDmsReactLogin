import { Navigate, Outlet, useLocation } from "react-router-dom";
import Header from "./Header";

function Layout() {
  const loc = useLocation();
  const jwt = localStorage.getItem("jwt");

  const noAuthRequired = ["/login", "/register"];
  const isNoAuthPage = noAuthRequired.includes(loc.pathname);

  if (!jwt && !isNoAuthPage) {
    return <Navigate to="/login" replace />;
  }  

  return (
    <>
      <Header />
      <main className="container mx-auto mt-8 px-3">
        <Outlet />
      </main>
    </>
  );
}

export default Layout;
