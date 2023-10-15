// shadril238
// Callbacks and Promises

const postListPromise = new Promise((resolve, reject) => {
    $.get('https://jsonplaceholder.typicode.com/posts', (data) => {
        resolve(data);
    }).fail((err) => {
        reject(new Error(`Error: ${err.status}`));
    });
})

postListPromise
.then((response) => {
    console.log("Call Success");
    console.log(response);
})
.catch((err) => {
    console.log("Call Failed");
    console.log(err);
})