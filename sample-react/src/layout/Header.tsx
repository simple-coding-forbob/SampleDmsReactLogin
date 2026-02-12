import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useAuthStore } from "../store/useAuthStore";

export default function Header() {
  const [isOpen, setIsOpen] = useState(false);
  const { loggedIn, logout } = useAuthStore();
  const nav = useNavigate();

  const logoutMenu = () => {
    logout();
    nav("/login");
  };

  return (
    <nav className="bg-gray-100 text-gray-800">
      <div className="bg-blue-700 text-white flex justify-between border-b items-center py-1 px-5">
        <div className="text font-bold">simple-coding</div>
        <div className="flex items-center gap-4">
          <button
            aria-label="햄버거 메뉴 열기/닫기"
            onClick={() => setIsOpen(!isOpen)}
          >
            ☰
          </button>
        </div>
      </div>
      <div
        className={`flex flex-col gap-4 transition-all duration-700
                                   md:flex-row md:justify-between md:items-center
                                   ${
                                     isOpen
                                       ? "max-h-1000"
                                       : "max-h-0 overflow-hidden"
                                   }
                                   `}
      >
        <ul
          className="flex flex-col gap-4 pl-5 py-2
                              md:flex-row
          "
        >
          <li>
            <Link to="/" className="hover:underline">
              홈
            </Link>
          </li>
          <li></li>
          <li className="group md:relative">
            <Link to="#" className="hover:underline">
              부서
            </Link>
            <ul
              className="md:absolute md:top-full md:left-0 md:opacity-0 md:invisible md:group-hover:opacity-100 
            md:group-hover:visible md:transition-all md:bg-white md:text-black md:w-42 md:p-2 md:rounded"
            >
              <li className="m-2">
                <Link to="/dept" className="hover:underline">
                  부서조회
                </Link>
              </li>
              <li className="m-2">
                <Link to="/add-dept" className="hover:underline">
                  부서추가
                </Link>
              </li>
            </ul>
          </li>
          <li className="group md:relative">
            <Link to="#" className="hover:underline">
              사원
            </Link>
            <ul
              className="md:absolute md:top-full md:left-0 md:opacity-0 md:invisible md:group-hover:opacity-100 
            md:group-hover:visible md:transition-all md:bg-white md:text-black md:w-42 md:p-2 md:rounded"
            >
              <li className="m-2">
                <Link to="/emp" className="hover:underline">
                  사원조회
                </Link>
              </li>
              <li className="m-2">
                <Link to="/add-emp" className="hover:underline">
                  사원추가
                </Link>
              </li>
            </ul>
          </li>
          <li className="group md:relative">
            <Link to="#" className="hover:underline">
              Faq
            </Link>
            <ul
              className="md:absolute md:top-full md:left-0 md:opacity-0 md:invisible md:group-hover:opacity-100 
            md:group-hover:visible md:transition-all md:bg-white md:text-black md:w-42 md:p-2 md:rounded"
            >
              <li className="m-2">
                <Link to="/faq" className="hover:underline">
                  Faq조회
                </Link>
              </li>
              <li className="m-2">
                <Link to="/add-faq" className="hover:underline">
                  Faq추가
                </Link>
              </li>
            </ul>
          </li>
          <li className="group md:relative">
            <Link to="#" className="hover:underline">
              Qna
            </Link>
            <ul
              className="md:absolute md:top-full md:left-0 md:opacity-0 md:invisible md:group-hover:opacity-100 
            md:group-hover:visible md:transition-all md:bg-white md:text-black md:w-42 md:p-2 md:rounded"
            >
              <li className="m-2">
                <Link to="/qna" className="hover:underline">
                  Qna조회
                </Link>
              </li>
              <li className="m-2">
                <Link to="/add-qna" className="hover:underline">
                  Qna추가
                </Link>
              </li>
            </ul>
          </li>
          <li className="group md:relative">
            <Link to="#" className="hover:underline">
              FileDb
            </Link>
            <ul
              className="md:absolute md:top-full md:left-0 md:opacity-0 md:invisible md:group-hover:opacity-100 
            md:group-hover:visible md:transition-all md:bg-white md:text-black md:w-42 md:p-2 md:rounded"
            >
              <li className="m-2">
                <Link to="/fileDb" className="hover:underline">
                  FileDb 조회
                </Link>
              </li>
              <li className="m-2">
                <Link to="/add-fileDb" className="hover:underline">
                  FileDb 추가
                </Link>
              </li>
            </ul>
          </li>
          <li className="group md:relative">
            <Link to="#" className="hover:underline">
              Gallery
            </Link>
            <ul
              className="md:absolute md:top-full md:left-0 md:opacity-0 md:invisible md:group-hover:opacity-100 
            md:group-hover:visible md:transition-all md:bg-white md:text-black md:w-42 md:p-2 md:rounded"
            >
              <li className="m-2">
                <Link to="/gallery" className="hover:underline">
                  Gallery 조회
                </Link>
              </li>
              <li className="m-2">
                <Link to="/add-gallery" className="hover:underline">
                  Gallery 추가
                </Link>
              </li>
            </ul>
          </li>
        </ul>
        <ul
          className="flex flex-col gap-4  pr-5 py-2
                               md:flex-row"
        >
          {!loggedIn && (
            <>
              <li>
                <Link to="/register">회원가입</Link>
              </li>
              <li>
                <Link to="/login">로그인</Link>
              </li>
            </>
          )}

          {loggedIn && (
            <>
              <li>
                <Link to="/mypage">마이페이지</Link>
              </li>
              <li>
                <button onClick={logoutMenu}>로그아웃</button>
              </li>
            </>
          )}
        </ul>
      </div>
    </nav>
  );
}
