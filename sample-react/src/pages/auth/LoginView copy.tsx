// src/pages/auth/LoginView.tsx
import { Meta } from "react-head";
import { Link } from "react-router-dom";

function LoginView() {


  return (
    <div className="flex items-center justify-center">
      <Meta name="description" content="로그인 페이지입니다." />
      <div className="w-full max-w-5xl rounded-lg flex">
        {/* 이미지 영역 */}
        <div className="hidden lg:flex lg:w-1/2 items-center justify-center">
          <img
            src="/images/puppy-1920_1280.webp"
            loading="lazy"
            alt="로그인"
            className="w-full h-full object-contain"
          />
        </div>

        {/* 로그인 폼 영역 */}
        <div className="w-full lg:w-1/2 p-8">
          <h3 className="text-4xl font-bold mb-6 text-center">simple-coding</h3>

          <form className="space-y-4">
            {/* 이메일 */}
            <div>
              <label htmlFor="email" className="block mb-1 font-medium">
                이메일
              </label>
              <input
                type="email"
                id="email"
                name="email"
                placeholder="이메일을 넣기"
                className="w-full border border-gray-300 rounded p-3 focus:outline-none focus:ring focus:ring-blue-500"
              />
            </div>

            {/* 비밀번호 */}
            <div>
              <label htmlFor="password" className="block mb-1 font-medium">
                비밀번호
              </label>
              <input
                type="password"
                id="password"
                name="password"
                placeholder="패스워드 넣기"
                className="w-full border border-gray-300 rounded p-3 focus:outline-none focus:ring focus:ring-blue-500"
              />
            </div>

            {/* 로그인 버튼 */}
            <button
              type="submit"
              className="w-full bg-blue-600 text-white p-3 rounded hover:bg-blue-700 transition"
            >
              Login
            </button>
          </form>

          <hr className="my-4" />

          <div className="space-y-2">
            <Link
              to="/"
              className="block w-full text-center bg-red-600 text-white p-3 rounded hover:bg-red-700 transition"
            >
              Login with Google
            </Link>
            <Link
              to="/"
              className="block w-full text-center bg-green-700 text-white p-3 rounded hover:bg-green-800 transition"
            >
              Login with Naver
            </Link>
            <Link
              to="/"
              className="block w-full text-center bg-yellow-700 text-white p-3 rounded hover:bg-yellow-800 transition"
            >
              Login with Kakao
            </Link>
          </div>

          <hr className="my-4" />

          <div className="text-center space-x-4">
            <Link
              to="#"
              className="text-sm text-gray-600 hover:underline"
            >
              비밀번호를 잊었나요?
            </Link>
            <Link
              to="/register"
              className="text-sm text-gray-600 hover:underline"
            >
              계정이 없나요!
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
}

export default LoginView;
