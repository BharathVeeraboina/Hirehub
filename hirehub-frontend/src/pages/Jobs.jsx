import { useEffect, useState } from "react";
import api from "../api/axios";
import { useNavigate } from "react-router-dom";

function Jobs() {
  const [jobs, setJobs] = useState([]);
  const [page, setPage] = useState(0);
  const [keyword, setKeyword] = useState("");
  const [location, setLocation] = useState("");
  const [salary, setSalary] = useState("");

  const navigate = useNavigate();

  const fetchJobs = async () => {
    const res = await api.get(`/api/jobs?page=${page}&size=5`);
    setJobs(res.data.content);
  };

  const searchJobs = async () => {
    const res = await api.get(`/api/jobs/search?keyword=${keyword}&page=0&size=5`);
    setJobs(res.data.content);
  };

  const filterJobs = async () => {
    const res = await api.get(
      `/api/jobs/filter?keyword=${keyword}&location=${location}&minSalary=${salary}&page=0&size=5`
    );
    setJobs(res.data.content);
  };

  useEffect(() => {
    fetchJobs();
  }, [page]);

  return (
    <div>
      <h2>Jobs</h2>

      <button onClick={() => navigate("/my-applications")}>
        My Applications
      </button>

      <br /><br />

      <input placeholder="Keyword" onChange={(e) => setKeyword(e.target.value)} />
      <input placeholder="Location" onChange={(e) => setLocation(e.target.value)} />
      <input placeholder="Min Salary" onChange={(e) => setSalary(e.target.value)} />

      <br /><br />

      <button onClick={searchJobs}>Search</button>
      <button onClick={filterJobs}>Filter</button>

      <hr />

      {jobs.map(job => (
        <div key={job.id}>
          <h3>{job.title}</h3>
          <p>{job.company}</p>
          <p>{job.location}</p>
          <p>{job.salary}</p>

          <button onClick={() => navigate(`/apply/${job.id}`)}>
            Apply
          </button>

          <hr />
        </div>
      ))}

      <button disabled={page === 0} onClick={() => setPage(page - 1)}>Prev</button>
      <button onClick={() => setPage(page + 1)}>Next</button>
    </div>
  );
}

export default Jobs;