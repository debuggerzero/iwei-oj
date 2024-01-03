import axios from "@/plugins/axios";
import RouterEnum from "@/constant/RouterEnum";

const uploadFile = (file) => {
  return axios.post(
    RouterEnum.BACK_END.UPLOAD_FILE,
    {
      file: file,
    },
    {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    }
  );
};

export default {
  uploadFile: uploadFile,
};
