import { useNavigate } from "react-router-dom";

function UserDashboard() {
  const navigate = useNavigate();

  return (
    <div>
      <h2>User Dashboard</h2>

      <button onClick={() => navigate("/jobs")}>
        Browse Jobs
      </button>

      <br /><br />

      <button onClick={() => navigate("/my-applications")}>
        My Applications
      </button>
    </div>
  );
}

export default UserDashboard;