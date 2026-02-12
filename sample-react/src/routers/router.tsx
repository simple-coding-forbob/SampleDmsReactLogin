import { lazy, Suspense } from "react";
import { createBrowserRouter } from "react-router-dom";
import Layout from "../layout/Layout";

const loading = <div>Loading...</div>;

const Home = lazy(() => import("../pages/Home"));

const DeptList = lazy(() => import("../pages/dept/DeptList"));
const AddDept = lazy(() => import("../pages/dept/AddDept"));
const DeptDetail = lazy(() => import("../pages/dept/DeptDetail"));

const EmpList = lazy(() => import("../pages/emp/EmpList"));
const AddEmp = lazy(() => import("../pages/emp/AddEmp"));
const EmpDetail = lazy(() => import("../pages/emp/EmpDetail"));

const FileDbList = lazy(() => import("../pages/filedb/FileDbList"));
const AddFileDb = lazy(() => import("../pages/filedb/AddFileDb"));

const LoginView = lazy(() => import("../pages/auth/LoginView"));
const RegisterView = lazy(() => import("../pages/auth/RegisterView"));
const Mypage = lazy(() => import("../pages/Mypage"));

const router = createBrowserRouter([
  {
    path: "/",
    element: (
      <Suspense fallback={loading}>
        <Layout />
      </Suspense>
    ),
    children: [
      { index: true, element: <Home /> },
      { path: "dept", element: <DeptList /> },
      { path: "add-dept", element: <AddDept /> },
      { path: "dept-detail/:dno", element: <DeptDetail /> },
      { path: "emp", element: <EmpList /> },
      { path: "add-emp", element: <AddEmp /> },
      { path: "emp-detail/:eno", element: <EmpDetail /> },
      { path: "fileDb", element: <FileDbList /> },
      { path: "add-fileDb", element: <AddFileDb /> },
      { path: "login", element: <LoginView /> },
      { path: "register", element: <RegisterView /> },
      { path: "mypage", element: <Mypage /> },
    ],
  },
]);

export default router;
