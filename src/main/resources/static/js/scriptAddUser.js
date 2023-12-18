const addUser = () => {
    const form = document.querySelector('#register');
    const inputUser = form.querySelector('input[name=username]');
    const inputPassword = form.querySelector('input[name=password]');

    form.addEventListener('submit', async (e) => {
    e.preventDefault();
        const url = new URL("/add", window.location.origin);
        url.searchParams.append('username', inputUser.value);
        url.searchParams.append('password', inputPassword.value);

        fetch(url.href, {
            method: 'POST',
        })
        .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.text(); // или response.text(), в зависимости от формата ответа
            })
            .then(data => {
                    document.querySelector('.query_Response').textContent = data;
            })
            .catch(error => {
                console.log('There was a problem with the fetch operation:', error);
                // Обработка ошибок здесь
            });
    });
}
addUser();