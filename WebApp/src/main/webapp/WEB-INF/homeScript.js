const uploadButton = document.getElementById("upload-button");
const fileInput = document.getElementById("file-input");
const fileList = document.getElementById("file-list");
const compressButton = document.getElementById("compress-button");
const contextPath = document.getElementById("contextPath").innerText;

// Trigger file input when the Upload button is clicked
uploadButton.addEventListener("click", () => {
    fileInput.click();
});

// Handle file input change
fileInput.addEventListener("change", (event) => {
    const files = event.target.files; // Get selected files

    // Begin sending files
    uploadFiles(files).then(() => {
        // Add files to the list
        files.forEach((file) => {
            addFileToList(file)
        });

        //Remove from input element
        fileInput.value = "";
    }, reason => {
        alert(reason);
    });
});

// Function to add a file to the list
/**
 *
 * @param {File} file
 */
function addFileToList(file) {
    // Create list item elements
    const li = document.createElement("li");
    const label = document.createElement("label");
    const removeButton = document.createElement("button");

    // Set content and styles
    label.textContent = file.name;
    removeButton.textContent = "Remove";
    removeButton.className = "remove-button";

    // Append label and remove button to list item
    li.appendChild(label);
    li.appendChild(removeButton);

    // Append list item to the file list
    fileList.appendChild(li);

    // Add remove functionality
    removeButton.addEventListener("click", () => {
        removeFile(file).then(() => li.remove(), reason => {
            alert(reason);
        });
    });
}

// Function to remove a file from the stored list
/**
 *
 * @param {File} fileToRemove
 * @returns {Promise<void>}
 */
async function removeFile(fileToRemove) {
    await fetch(contextPath + "/api/upload", {
        method: 'DELETE',
        body: fileToRemove.name
    })
}

/**
 *
 * @param {File[]} uploadedFiles
 * @returns {Promise<void>}
 * @throws {Error}
 */

// Upload files to the server
async function uploadFiles(uploadedFiles) {
    if (uploadedFiles.length === 0) {
        throw new Error("No files to upload.");
    }

    const formData = new FormData();
    uploadedFiles.forEach((file) => {
        formData.append("files", file); // Add files to FormData
    });
    const response = await fetch(contextPath + "/api/upload", {
        method: "POST",
        body: formData,
    });
    if (response.ok) {
    } else {
        throw new Error("Failed to upload files.");
    }
}

function IsValidFilename(filename) {
    // Define invalid characters for filenames
    const invalidCharsRegex = /[<>:"/\\|?*\x00-\x1F]/; // Includes control characters

    if (filename.length === 0) return false;

    // Check for invalid characters
    if (invalidCharsRegex.test(filename)) {
        return false; // Invalid due to special characters
    }

    // Check for reserved names (Windows)
    const reservedNames = /^(con|prn|aux|nul|com[0-9]|lpt[0-9])(\.|\s|$)/i;
    if (reservedNames.test(filename)) {
        return false; // Invalid due to reserved name
    }

    // Check if the filename ends with a space or period
    if (filename.endsWith(" ") || filename.endsWith(".")) {
        return false; // Invalid due to ending with space/period
    }

    // All checks passed, the filename is valid
    return true;
}

compressButton.onclick = async () => {
    /** @type {string} */
    const outputName = document.getElementById("output-name").value;
    const errorDisplay = document.getElementById("error");
    if (!IsValidFilename(outputName)) {
        errorDisplay.style.opacity = "1";
    } else {
        errorDisplay.style.opacity = "0";
        const response = fetch(contextPath + "/api/upload", {
           method: 'GET'
        });
        /** @type {HTMLDialogElement} */
        const dialog = document.getElementById("dialog");
        const waitingDiv = document.getElementById("waiting")
        const downloadButton = document.getElementById("download-button");
        waitingDiv.hidden = false;
        downloadButton.hidden = true;
        dialog.showModal();
        response.then(() => {
            waitingDiv.hidden = true;
            downloadButton.hidden = false;
            downloadButton.onclick = ()=> {

            };
        });
    }
}