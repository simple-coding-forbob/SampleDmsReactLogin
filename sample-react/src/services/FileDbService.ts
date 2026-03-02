import common from "../common/CommonService";
import type { IFileDb } from "../types/IFileDb";

const getAll = (searchKeyword: string, page: number, size: number) => {
  return common.get("/fileDb", {
    params: { searchKeyword, page, size },
  });
};

const remove = (uuid: number) => {
  return common.delete(`/fileDb/${uuid}`);
};

const insert = (data: IFileDb) => {
  const formData = new FormData();
  formData.append("fileTitle", data.fileTitle);
  formData.append("fileContent", data.fileContent);
  if (data.fileData) {
    formData.append("fileData", data.fileData);
  }

  return common.post("/fileDb", formData, {
    headers: { "Content-Type": "multipart/form-data" },
  });
};

const FiledbService = {
  getAll,
  remove,
  insert,
};

export default FiledbService;
