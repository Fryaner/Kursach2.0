const headerFunction = () => {
    const burgerBtn = document.querySelector('#burgermenu');
    const burgerNavigation = document.querySelector('#headerBurger');
    const burgerOther = document.querySelector('#navigationBurger2');
    const postcardsAndClosureMenu = (e) => {
        burgerNavigation.classList.toggle('active');
    }

    burgerBtn.addEventListener('click', postcardsAndClosureMenu);
}
const tabFunction = () => {
    const buttons = document.querySelectorAll('[data-gendr]')

    const tabsActive = (e) => {
        e.preventDefault();
        const container = document.querySelector('.title__tabs');
        const targetElement = e.target;
        const targetTabContentId = targetElement.dataset.gendr;
        const targetTabContent = document.querySelector(targetTabContentId);

        const activeTab = container.querySelector('.active');
        const activeTabContentId = activeTab.dataset.gendr;
        const activeTabContent = document.querySelector(activeTabContentId);

        if (targetElement.classList.contains('active')) {
            return;
        }
        console.log(activeTab)
        targetElement.classList.add('active');
        targetTabContent.classList.add('active');
        activeTabContent.classList.remove('active');
        activeTab.classList.remove('active');
    }

    buttons.forEach((btn) => {
        btn.addEventListener('click', tabsActive)
    })
}
const sumProducts = () => {

}
tabFunction();
headerFunction();