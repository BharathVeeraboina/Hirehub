import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

function Applications() {
  const { jobId } = useParams();
  const [apps, setApps] = useState([]);

  useEffect(() => {
    fetch(`http://localhost:8080/api/applications/job/${jobId}`, {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    })
      .then((res) => res.json())
      .then((data) => setApps(data))
      .catch((err) => console.error(err));
  }, [jobId]);

  return (
    <div>
      <h2>Applications</h2>

      {apps.map((app) => (
        <div key={app.id}>
          <p>User: {app.userEmail}</p>
          <p>Status: {app.status}</p>
        </div>
      ))}
    </div>
  );
}

export default Applications;