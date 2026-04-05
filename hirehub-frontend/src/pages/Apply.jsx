import { useParams } from "react-router-dom";
import { useState } from "react";
import api from "../api/axios";

function Apply() {
  const { jobId } = useParams();
  const [file, setFile] = useState(null);

  const handleApply = async () => {
    const formData = new FormData();
    formData.append("file", file);

    await api.post(`/api/applications/${jobId}/apply-with-resume`, formData);

    alert("Applied successfully");
  };

  return (
    <div>
      <h2>Apply Job</h2>

      <input type="file" onChange={(e) => setFile(e.target.files[0])} />

      <br /><br />

      <button disabled={!file} onClick={handleApply}>
        Apply
      </button>
    </div>
  );
}

export default Apply;