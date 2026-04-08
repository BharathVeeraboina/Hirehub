import { useState } from "react";

function PostJob() {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");

  const handlePost = async (e) => {
    e.preventDefault();

    try {
      await fetch("http://localhost:8080/api/jobs", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
        body: JSON.stringify({ title, description }),
      });

      alert("Job Posted!");
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div>
      <h2>Post Job</h2>

      <form onSubmit={handlePost}>
        <input
          type="text"
          placeholder="Job Title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />

        <br /><br />

        <textarea
          placeholder="Job Description"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
        />

        <br /><br />

        <button type="submit">Post Job</button>
      </form>
    </div>
  );
}

export default PostJob;