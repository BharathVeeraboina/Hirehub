import { useEffect, useState } from "react";
import api from "../api/axios";

function MyApplications() {
  const [apps, setApps] = useState([]);

  useEffect(() => {
    api.get("/api/applications/my")
      .then(res => setApps(res.data));
  }, []);

  return (
    <div>
      <h2>My Applications</h2>

      {apps.map(app => (
        <div key={app.id}>
          <p>{app.job.title}</p>

          <p style={{
            color:
              app.status === "ACCEPTED" ? "green" :
              app.status === "REJECTED" ? "red" : "orange"
          }}>
            {app.status}
          </p>

          <hr />
        </div>
      ))}
    </div>
  );
}

export default MyApplications;