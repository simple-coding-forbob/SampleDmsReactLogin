import { lazy, Suspense } from "react";
import { createBrowserRouter } from "react-router-dom";
import Layout from "../layout/Layout";

// 메뉴 등록 시 2가지 중에 1가지를 사용하세요
// 일반 로딩 사용법: import 페이지명 from "페이지경로";
// lazy 로딩 사용법: const 상수 = lazy(() => import("페이지경로"));

// 공통 로딩 컴포넌트
const loading = <div>Loading...</div>;

// 페이지 lazy 로딩
const Home = lazy(() => import("../pages/Home"));

// 부서
const DeptList = lazy(() => import("../pages/dept/DeptList"));
const AddDept = lazy(() => import("../pages/dept/AddDept"));
const DeptDetail = lazy(() => import("../pages/dept/DeptDetail"));

// 사원
const EmpList = lazy(() => import("../pages/emp/EmpList"));
const AddEmp = lazy(() => import("../pages/emp/AddEmp"));
const EmpDetail = lazy(() => import("../pages/emp/EmpDetail"));

// QnA

// 공지사항

// 이벤트 공지사항

// 파일 DB
const FileDbList = lazy(() => import("../pages/filedb/FileDbList"));
const AddFileDb = lazy(() => import("../pages/filedb/AddFileDb"));

// 갤러리

// 회원/로그인
const LoginView = lazy(() => import("../pages/auth/LoginView"));
const RegisterView = lazy(() => import("../pages/auth/RegisterView"));
const Mypage = lazy(() => import("../pages/Mypage"));

// 자유게시판

// 뉴스게시판

// 예약 게시판

// 예약 게시판

// 공용차량

// 예약 차량

// 전자결재: 템플릿

// 전자결재: 문서

// 전자결재: 결재

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
      // 부서
      { path: "dept", element: <DeptList /> },
      { path: "add-dept", element: <AddDept /> },
      { path: "dept-detail/:dno", element: <DeptDetail /> },
      // 사원
      { path: "emp", element: <EmpList /> },
      { path: "add-emp", element: <AddEmp /> },
      { path: "emp-detail/:eno", element: <EmpDetail /> },

      // 파일db 업로드
      { path: "fileDb", element: <FileDbList /> },
      { path: "add-fileDb", element: <AddFileDb /> },

      // 회원가입/로그인/마이페이지
      { path: "login", element: <LoginView /> },
      { path: "register", element: <RegisterView /> },
      { path: "mypage", element: <Mypage /> },
    ],
  },
]);

export default router;
