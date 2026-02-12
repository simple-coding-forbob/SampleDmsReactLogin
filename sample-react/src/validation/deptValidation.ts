import * as yup from "yup";
import messages from "../common/messages";

const deptValidation = yup.object({
  dname: yup.string().required(messages.required),
  loc: yup.string().required(messages.required),
});

export default deptValidation;
