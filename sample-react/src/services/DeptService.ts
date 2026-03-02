
import common from "../common/CommonService";
import type { IDept } from "../types/IDept";

const getAll = (searchKeyword: string, page: number, size: number) => {
  return common.get("/dept", {
    params: { searchKeyword, page, size },
  });
};

const get = (dno: number) => {
  return common.get(`/dept/${dno}`);
};

const insert = (data: IDept) => {
  return common.post("/dept", data);
};

const update = (dno: number, data: IDept) => {
  return common.put(`/dept/${dno}`, data);
};

const remove = (dno: number) => {
  return common.delete(`/dept/${dno}`);
};

const DeptService = {
  getAll,
  get,
  insert,
  update,
  remove,
};

export default DeptService;
