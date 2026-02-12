
import axiosInstance from "../common/axiosInstance";
import type { IDept } from "../types/IDept";

const getAll = (searchKeyword: string, page: number, size: number) => {
  return axiosInstance.get("/dept", {
    params: { searchKeyword, page, size },
  });
};

const get = (dno: number) => {
  return axiosInstance.get(`/dept/${dno}`);
};

const insert = (data: IDept) => {
  return axiosInstance.post("/dept", data);
};

const update = (dno: number, data: IDept) => {
  return axiosInstance.put(`/dept/${dno}`, data);
};

const remove = (dno: number) => {
  return axiosInstance.delete(`/dept/${dno}`);
};

const DeptService = {
  getAll,
  get,
  insert,
  update,
  remove,
};

export default DeptService;
