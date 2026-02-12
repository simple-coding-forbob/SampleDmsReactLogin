
import axiosInstance from "../common/axiosInstance";
import type { IFileDb } from "../types/IFileDb";

const getAll = (searchKeyword: string, page: number, size: number) => {
  return axiosInstance.get("/fileDb", {
    params: { searchKeyword, page, size },
  });
};

const remove = (uuid: number) => {
  return axiosInstance.delete(`/fileDb/${uuid}`);
};

const insert = (data: IFileDb) => {
  const formData = new FormData();
  formData.append("fileTitle", data.fileTitle);
  formData.append("fileContent", data.fileContent);
  if (data.fileData) {
    formData.append("fileData", data.fileData);
  }

  return axiosInstance.post("/fileDb", formData, {
    headers: { "Content-Type": "multipart/form-data" },
  });
};

const FiledbService = {
  getAll,
  remove,
  insert,
};

export default FiledbService;
