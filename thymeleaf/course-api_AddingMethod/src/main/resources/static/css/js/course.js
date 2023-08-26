const courseList = document.getElementById("course-list");
const filterArea = document.getElementById("filterArea");

const fetchCourses = async (name, typeValue, topicValue) => {
    try {
        let apiUrl = "http://localhost:8080/api/v1/courses?";
        if (name) {
            apiUrl += `name=${encodeURIComponent(name)}&`;
        }
        if (typeValue) {
            apiUrl += `type=${encodeURIComponent(typeValue)}&`;
        }
        if (topicValue) {
            apiUrl += `topic=${encodeURIComponent(topicValue)}`;
        }
        const response = await fetch(apiUrl);
        const data = await response.json();
        return data;
    } catch (error) {
        console.error("Error fetching data:", error);
        return [];
    }
};

const fetchTopics = async () => {
    try {
        const response = await fetch("http://localhost:8080/api/v1/courses/topics");
        const data = await response.json();
        return data;
    } catch (error) {
        console.error("Error fetching topics:", error);
        return [];
    }
};

const nameInput = document.getElementById("nameInput");
nameInput.addEventListener("keyup", event => {
    if (event.key === "Enter") {
        const nameValue = nameInput.value.trim();
        if (nameValue === "") {
            alert("Tên khóa học không được để trống!");
        } else {
            showCourses(nameValue, null, null);
        }
    }
});

const createRadioButton = (topic) => {
    const topicItem = document.createElement("div");
    topicItem.className = "topic-item input-group d-flex align-items-center mb-1";
    const inputTopicItem = document.createElement("input");
    inputTopicItem.type = "radio";
    inputTopicItem.value = topic;
    inputTopicItem.id = topic;
    inputTopicItem.name = "topic";
    inputTopicItem.addEventListener("change", () => {
        showCourses(null, null, inputTopicItem.value);
    });

    const labelTopicItem = document.createElement("label");
    labelTopicItem.className = "ms-2 fs-5";
    labelTopicItem.for = topic;
    labelTopicItem.innerText = topic;

    topicItem.appendChild(inputTopicItem);
    topicItem.appendChild(labelTopicItem);

    filterArea.appendChild(topicItem);
};
const createTopics = async () => {
    const topics = await fetchTopics();
    topics.forEach(topic => createRadioButton(topic));
}

const showCourses = async (name, typeValue, topicValue) => {
    const courses = await fetchCourses(name, typeValue, topicValue);
    courseList.innerHTML = "";
    if (courses.length === 0) {
        const noResult = document.createElement("p");
        noResult.textContent = "Không tìm thấy khóa học phù hợp.";
        courseListDiv.appendChild(noResult);
    } else {
        courses.forEach(course => {
            const courseItem = document.createElement("div");
            courseItem.className = "col-md-4";

            const courseLink = document.createElement("a");
            courseLink.href = `./detail.html?id=${course.id}`;

            const courseCard = document.createElement("div");
            courseCard.className = "course-item shadow-sm rounded mb-4";

            const courseImage = document.createElement("div");
            courseImage.className = "course-item-image";
            const image = document.createElement("img");
            image.src = course.thumbnail;
            image.alt = "khoa hoc";
            courseImage.appendChild(image);

            const courseInfo = document.createElement("div");
            courseInfo.className = "course-item-info p-3";
            const title = document.createElement("h2");
            title.className = "fs-5 mb-3 text-dark";
            title.textContent = course.name;
            const type = document.createElement("p");
            type.className = "type fw-light text-black-50";
            type.textContent = course.type;

            courseInfo.appendChild(title);
            courseInfo.appendChild(type);

            courseCard.appendChild(courseImage);
            courseCard.appendChild(courseInfo);

            courseLink.appendChild(courseCard);
            courseItem.appendChild(courseLink);

            courseList.appendChild(courseItem);
        });
    }
}
createTopics();
showCourses();