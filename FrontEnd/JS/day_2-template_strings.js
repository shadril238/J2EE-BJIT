// shadril238
// JS Template Strings

const firstName = 'Shadril';
const lastName = 'Shifat';

function getFullName(firstName, lastName) {
    return `${firstName} ${lastName}`;
}

const mGreetings = `Hello ${getFullName(firstName, lastName)}`;

console.log(mGreetings);