const profilePic = document.getElementById("profile-pic");
const fileInput = document.getElementById("file-input");

// When image is clicked, trigger hidden file input
profilePic.addEventListener("click", () => fileInput.click());

// When user selects a new image
fileInput.addEventListener("change", event => {
    const file = event.target.files[0];
    if (file) {
    profilePic.src = URL.createObjectURL(file);
    }
});

const form = document.getElementById("modifyUserForm");
const modifyBtn = document.getElementById("modifyBtn");

// Listen for *any* input/change inside the form
form.addEventListener("input", () => {
    modifyBtn.disabled = false;
});

form.addEventListener("change", () => {
    modifyBtn.disabled = false;
});
