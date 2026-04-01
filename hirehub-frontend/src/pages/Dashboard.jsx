import { useEffect, useState } from "react";
import api from "../api/axios";
import { useNavigate } from "react-router-dom";

function Dashboard() {
  const [jobs, setJobs] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    api.get("/api/jobs/my")
      .then(res => setJobs(res.data));
  }, []);

  return (
    <div>
      <h2>Recruiter Dashboard</h2>

      <button onClick={() => navigate("/create-job")}>
        Create Job
      </button>

      <hr />

      {jobs.map(job => (
        <div key={job.id}>
          <h3>{job.title}</h3>
          <p>Applications: {job.applicationsCount}</p>

          <button onClick={() => navigate(`/applications/${job.id}`)}>
            View Applicants
          </button>

          <hr />
        </div>
      ))}
    </div>
  );
}

export default Dashboard;