window.onload = (
    async () => {
        const nav = document.createElement('nav');
        nav.classList.add('ui', 'top', 'secondary', 'pointing', 'borderless', 'menu');

        const item = document.createElement("a");
        item.setAttribute("href","/");
        item.classList.add("logo");
        item.textContent="LibApp";
        const logo = document.createElement("div")
        logo.classList.add("header", "item");
        logo.appendChild(item);
        nav.appendChild(logo);

        const links = [
            {text: 'Home', href: '/'},
            {text: 'Users', subItems: [
                    {text: 'List', href: '/users'},
                    {text: 'Create', href: '/users/create'}
                ]},
            {
                text: 'Employees', subItems: [
                    {text: 'List', href: '/employee'},
                    {text: 'Create', href: '/employee/create'}
                ]
            },
            {text: 'Books', subItems: [
                {text: 'Animals', href: '/animal-book'},
                {text: 'Sports', href: '/sport-book'}
                ]
            },
            {text: 'Libraries', href: '/library'}
        ];

        links.forEach(link => {
            if (link.subItems) {
                const dropdown = document.createElement('div');
                dropdown.classList.add('ui', 'dropdown', 'item');
                dropdown.textContent = link.text;

                const menu = document.createElement('div');
                menu.classList.add('menu');

                link.subItems.forEach(subItem => {
                    const subMenuItem = criarItem(subItem.text, subItem.href);
                    menu.appendChild(subMenuItem);
                });

                dropdown.appendChild(menu);
                nav.appendChild(dropdown);
            } else {
                const item = criarItem(link.text, link.href);
                nav.appendChild(item);
            }
        });

        document.body.insertBefore(nav, document.body.childNodes[0]);
        $(document).ready(function(){
            $('.ui.dropdown').dropdown();
        });

        function criarItem(text, href) {
            const item = document.createElement('a');
            item.classList.add('item');
            item.setAttribute('href', href);
            item.href == item.baseURI && item.classList.add("active")
            item.textContent = text;
            return item;
        }

    }
)()