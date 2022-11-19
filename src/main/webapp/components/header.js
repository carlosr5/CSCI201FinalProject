class Header extends HTMLElement {
    constructor() {
        super();
    }

    connectedCallback() {
        this.innerHTML = ``;
    }
}

customElements.define('header-component', Header);