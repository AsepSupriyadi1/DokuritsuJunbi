let listPost = null;

const likeSections = document.querySelectorAll(".like-section");

const getAllPost = async () => {
  await fetch("http://localhost:8080/api/post")
    .then((response) => response.json())
    .then((data) => {
      listPost = data;
      console.log(data); // Pindahkan console.log ke sini
    })
    .catch((error) => {
      console.error("Error:", error);
    });
};

function fetchLikePost() {
  const thumbs = document.querySelectorAll(".thumbs");
  const thumbsUp = document.querySelectorAll(".thumbsUp");
  const totalLikes = document.querySelectorAll(".totalLikes");
  const totalComments = document.querySelectorAll(".totalComments");

  getAllPost().then(() => {
    let i = 0;
    while (i < listPost.length) {
      if (listPost[i].isLiked === true) {
        thumbsUp[i].classList.remove("d-none");
        thumbs[i].classList.add("d-none");
      } else if (listPost[i].isLiked === false) {
        thumbsUp[i].classList.add("d-none");
        thumbs[i].classList.remove("d-none");
      }

      if (listPost[i].totalLiked <= 1) {
        totalLikes[i].textContent = listPost[i].totalLiked + " like";
      } else if (listPost[i].totalLiked > 1) {
        totalLikes[i].textContent = listPost[i].totalLiked + " likes";
      }

      if (listPost[i].totalComment <= 1) {
        totalComments[i].textContent = listPost[i].totalComment + " Comment";
      } else if (listPost[i].totalComment > 1) {
        totalComments[i].textContent = listPost[i].totalComment + " Comments";
      }

      i++;
    }
  });
}

const handleLike = async (postId) => {
  await fetch("http://localhost:8080/api/likePost/" + postId).then(() => {
    fetchLikePost();
  });
};

fetchLikePost();

let postLikesList = null;

const getAllPostLikes = async (postId) => {
  await fetch("http://localhost:8080/api/post/allLikes/" + postId)
    .then((response) => response.json())
    .then((data) => {
      postLikesList = data;
    })
    .catch((error) => {
      console.error("Error:", error);
    });
};

const fetchAllPostLikes = (postId) => {
  const likesTable = document.querySelector("#likesTable");
  const likeModal = document.querySelector("#likeModal");

  getAllPostLikes(postId).then(() => {
    if (postLikesList.length == 0) {
      $(document).ready(function () {
        $("#likeModal").modal("hide");
      });
      return null;
    }

    let result = "";
    let i = 0;
    while (i < postLikesList.length) {
      const allLikes = `

                        <tr>
                            <td class="align-middle">
                                <img src="img/bg-01.jpg" alt="" class="me-2" style="width: 40px; height: 40px; object-fit: cover; border-radius: 100%;">
                                <span>${postLikesList[i].fullName}</span>
                            </td>
                            <td class="text-end">
                                <a href="index.html" class="btn btn-outline-secondary">View Profile </a>
                                <button class="btn btn-primary">Connect </button>
                            </td>
                        </tr>

                    `;

      result += allLikes;

      i++;
    }

    likesTable.innerHTML = result;
    $(document).ready(function () {
      $("#likeModal").modal("show");
    });
  });
};

const commentSection = document.querySelectorAll(".commentSection");
const commentTextArea = document.querySelectorAll(".commentTextArea");
const showCommentSectionBtn = document.querySelectorAll(".showCommentSection");
const comentsContainer = document.querySelectorAll(".all-coments-container");
let listPostComment = null;
let commentsContainerGlobalIndex = null;

const fetchAllComments = (postId, containerIndex) => {
  getAllPostComment(postId)
    .then(() => {
      let result = "";
      let commentIndex = 0;
      while (commentIndex < listPostComment.length) {
        let replyResult = "";

        if (listPostComment[commentIndex].reply.length !== 0) {
          let replyIndex = 0;
          while (replyIndex < listPostComment[commentIndex].reply.length) {
            const replyELement = `

                  <div class="comment" id="comment-1">

                      <div class="comment-content">

                          <a href="#comment-1" class="comment-border-link">
                              <span class="sr-only">Jump to comment-1</span>
                          </a>

                          <div class="comment-voting">
                              <img src="../img/Logo_Institut_Teknologi_Bandung.png"
                                  alt="">
                          </div>

                          <div class="comment-body-container">
                              <div class="comment-body">
                                  <div class="comment-info">
                                      <div>
                                          <a href="#" class="comment-author">${listPostComment[commentIndex].reply[replyIndex].nameReplySender}</a>
                                          <p>  ${listPostComment[commentIndex].reply[replyIndex].nameHeadlineSender !== null ? listPostComment[commentIndex].reply[replyIndex].nameHeadlineSender : ""}</p>
                                      </div>

                                      <p class="m-0">
                                          &bull; 4 days ago
                                      </p>
                                  </div>

                                  <p class="mb-0">
                                    <b>${listPostComment[commentIndex].reply[replyIndex].nameTag}</b> ${listPostComment[commentIndex].reply[replyIndex].desc}
                                  </p>
                              </div>

                              <div>

                                  <small class="fw-bold cursor-pointer replyButton"><u>
                                          Reply</u></small>

                                  <div class="my-fade d-none replySection">
                                      <form class="mt-2 replyForm">
                                          <div class="d-flex">
                                              <div>
                                                  <img src="img/bg-01.jpg" alt=""
                                                      class="me-2"
                                                      style="width: 40px; height: 40px; object-fit: cover; border-radius: 100%;">
                                              </div>

                                              <div>

                                                  <input type="hidden" name="commentId" value="${listPostComment[commentIndex].reply[replyIndex].commentId}">
                                                  <input type="hidden" name="postId" value="${listPostComment[commentIndex].reply[replyIndex].postId}">

                                                  <textarea name="textArea"
                                                      placeholder="Add a reply..."
                                                      cols="100"
                                                      class="form-control bg-light"></textarea>

                                                  <button
                                                      class="btn btn-primary mt-1">Send <i
                                                          class="fa-regular fa-paper-plane"></i></button>
                                              </div>

                                          </div>

                                      </form>
                                  </div>

                              </div>
                          </div>
                      </div>
                </div>
            `;
            replyResult += replyELement;
            replyIndex++;
          }
        }

        const comentElement =
          `
                  <div class="comment" id="comment-1">

                    <div class="comment-content">

                        <a href="#comment-1" class="comment-border-link">
                            <span class="sr-only">Jump to comment-1</span>
                        </a>


                        <div class="comment-voting">
                            <img src="../img/Logo_Institut_Teknologi_Bandung.png" alt="">
                        </div>


                        <div class="comment-body-container">
                            <div class="comment-body">
                                <div class="comment-info">
                                    <div>
                                        <a href="#" class="comment-author">${listPostComment[commentIndex].nameSender}</a>
                                        <p>${listPostComment[commentIndex].headlineSender !== null ? listPostComment[commentIndex].headlineSender : ""}</p>
                                    </div>

                                    <p class="m-0">
                                        &bull; 4 days ago
                                    </p>
                                </div>

                                <p class="mb-0">
                                ${listPostComment[commentIndex].desc}
                                </p>
                            </div>

                            <div>

                                <small class="fw-bold cursor-pointer replyButton"><u>
                                        Reply</u></small>

                                <div class="my-fade d-none replySection">
                                    <form class="mt-2 replyForm">
                                        <div class="d-flex">
                                            <div>
                                                <img src="img/bg-01.jpg" alt="" class="me-2"
                                                    style="width: 40px; height: 40px; object-fit: cover; border-radius: 100%;">
                                            </div>

                                            <div>
                                                <input type="hidden" name="commentId" value="${listPostComment[commentIndex].commentId}">
                                                <input type="hidden" name="postId" value="${listPostComment[commentIndex].postId}">
                                                <textarea name="textArea"
                                                    placeholder="Add a reply..." cols="100"
                                                    class="form-control bg-light"></textarea>

                                                <button class="btn btn-primary mt-1">Send <i
                                                        class="fa-regular fa-paper-plane"></i></button>
                                            </div>
                                        </div>
                                    </form>
                                </div>


                            </div>
                        </div>



                    </div>




                    <div class="replies">` +
          replyResult +
          `</div>

              </div>
        `;

        result += comentElement;
        commentIndex++;
      }

      comentsContainer[containerIndex].innerHTML = result;
    })
    .then(() => {
      const replySection = document.querySelectorAll(".replySection");
      const replyButton = document.querySelectorAll(".replyButton");

      replyButton.forEach((element, index) => {
        element.addEventListener("click", (event) => {
          for (let i = 0; i < replySection.length; i++) {
            !replySection[i].classList.contains("d-none") ? replySection[i].classList.add("d-none") : null;
          }

          replySection[index].classList.remove("d-none");
        });
      });

      postReply();
    });
};

function postReply() {
  const replyForm = document.querySelectorAll(".replyForm");
  const textArea = document.getElementsByName("textArea");
  const commentId = document.getElementsByName("commentId");
  const postId = document.getElementsByName("postId");

  replyForm.forEach((element, index) => {
    element.addEventListener("submit", async (e) => {
      e.preventDefault();

      const data = {
        postId: parseInt(postId[index].value),
        commentId: parseInt(commentId[index].value),
        desc: textArea[index].value,
      };

      if (data.desc.split(" ").join("").length == 0) {
        textArea[index].value = "";
        console.log("Data Kosong");
        console.log(data.desc.split(" ").join("").length);
        return null;
      }

      console.log(data.desc.split(" ").join("").length);
      console.log("Data ada");

      await fetch("http://localhost:8080/api/reply/save", {
        headers: {
          "Content-Type": "application/json",
        },
        method: "POST",
        body: JSON.stringify(data),
      })
        .then(() => {
          fetchAllComments(data.postId, commentsContainerGlobalIndex);
          fetchLikePost();
          textArea[index].value = "";
        })
        .catch((error) => {
          console.error("Error:", error);
        });
    });
  });
}

const getAllPostComment = async (postId) => {
  await fetch("http://localhost:8080/api/post/allComment/" + postId)
    .then((response) => response.json())
    .then((data) => {
      listPostComment = data;
      console.log(listPostComment);
    })
    .catch((error) => {
      console.error("Error:", error);
    });
};

showCommentSectionBtn.forEach((element, index) => {
  element.addEventListener("click", (event) => {
    let post = showCommentSectionBtn[index].getAttribute("data-post");

    for (let i = 0; i < commentSection.length; i++) {
      !commentSection[i].classList.contains("d-none") ? commentSection[i].classList.add("d-none") : null;
    }
    commentSection[index].classList.remove("d-none");
    commentTextArea[index].focus();

    commentsContainerGlobalIndex = index;

    fetchAllComments(post, index);
  });
});

const formComment = document.querySelectorAll(".formComment");

formComment.forEach((element, index) => {
  element.addEventListener("submit", async (event) => {
    event.preventDefault();
    let postId = document.getElementsByName("commentPostId");
    let commentTxt = document.getElementsByName("commentTextArea");
    const data = {
      postId: postId[index].value,
      desc: commentTxt[index].value,
    };

    if (data.desc.split(" ").join("").length == 0) {
      commentTxt[index].value = "";
      console.log("Data Kosong");
      console.log(data.desc.split(" ").join("").length);
      return null;
    }

    console.log(data.desc.split(" ").join("").length);
    console.log("Data ada");

    await fetch("http://localhost:8080/api/comment/save", {
      headers: {
        "Content-Type": "application/json",
      },
      method: "POST",
      body: JSON.stringify(data),
    })
      .then(() => {
        fetchAllComments(data.postId, commentsContainerGlobalIndex);
        fetchLikePost();
        commentTxt[index].value = "";
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  });
});
