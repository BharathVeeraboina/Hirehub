import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import api from "../api/axios";

function Applicants() {
  const { jobId } = useParams();
  const [apps, setApps] = useState([]);

  const fetchApps = async () => {
    const res = await api.get(`/api/applications/job/${jobId}`);
    setApps(res.data);
  };

  useEffect(() => {
    fetchApps();
  }, []);

  const updateStatus = async (id, status) => {
    await api.put(`/api/applications/${id}/status?status=${status}`);
    fetchApps();
  };

  return (
    <div>
      <h2>Applicants</h2>

      {apps.map(app => (
        <div key={app.id}>
          <p>{app.user.name}</p>
          <p>{app.user.email}</p>

          <button onClick={() => updateStatus(app.id, "ACCEPTED")}>
            Accept
          </button>

          <button onClick={() => updateStatus(app.id, "REJECTED")}>
            Reject
          </button>

          <hr />
        </div>
      ))}
    </div>
  );
}

export default Applicants;