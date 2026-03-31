import { useEffect } from "react";
import { HeadProvider } from "react-head";
import { RouterProvider } from "react-router-dom";
import "./App.css";
import router from "./routers/router";
import AuthService from "./services/AuthService";
import { useAuthStore } from "./store/useAuthStore";

function App() {
  const { login } = useAuthStore();

  // TODO: 웹브라우저 새로고침 시 다시 벡엔드에 로그인 상태를 확인해서 공유저장소에 로그인 상태를 저장합니다.
  // TODO: 새로고침하면 js 값은 모두 사라집니다. 그래서 아래 코드가 필요합니다.
  useEffect(() => {
    const checkLogin = async () => {
        await AuthService.me(); // 서버에서 로그인 상태 확인
        login(); // 유효하면 상태 업데이트
    };
    checkLogin();
  }, [login]);

  return (
    <HeadProvider>
      <RouterProvider router={router} />
    </HeadProvider>
  );
}

export default App;
