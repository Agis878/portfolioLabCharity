const userData = {
    categories: [],
    quantity: 0,
    institution: null,
    street: '',
    city: '',
    zipCode: '',
    pickUpDate: '',
    pickUpTime: '',
    pickUpComment: '',
};


const list1 = document.getElementById('list1');
const list2 = document.getElementById('list2');

function goBack() {
    window.history.back();
}
document.addEventListener("DOMContentLoaded", function () {

    /**
     * Form Select
     */
    class FormSelect {
        constructor($el) {
            this.$el = $el;
            this.options = [...$el.children];
            this.init();
        }

        init() {
            this.createElements();
            this.addEvents();
            this.$el.parentElement.removeChild(this.$el);
        }

        createElements() {
            // Input for value
            this.valueInput = document.createElement("input");
            this.valueInput.type = "text";
            this.valueInput.name = this.$el.name;

            // Dropdown container
            this.dropdown = document.createElement("div");
            this.dropdown.classList.add("dropdown");

            // List container
            this.ul = document.createElement("ul");

            // All list options
            this.options.forEach((el, i) => {
                const li = document.createElement("li");
                li.dataset.value = el.value;
                li.innerText = el.innerText;

                if (i === 0) {
                    // First clickable option
                    this.current = document.createElement("div");
                    this.current.innerText = el.innerText;
                    this.dropdown.appendChild(this.current);
                    this.valueInput.value = el.value;
                    li.classList.add("selected");
                }

                this.ul.appendChild(li);
            });

            this.dropdown.appendChild(this.ul);
            this.dropdown.appendChild(this.valueInput);
            this.$el.parentElement.appendChild(this.dropdown);
        }

        addEvents() {
            this.dropdown.addEventListener("click", e => {
                const target = e.target;
                this.dropdown.classList.toggle("selecting");

                // Save new value only when clicked on li
                if (target.tagName === "LI") {
                    this.valueInput.value = target.dataset.value;
                    this.current.innerText = target.innerText;
                }
            });
        }
    }

    document.querySelectorAll(".form-group--dropdown select").forEach(el => {
        new FormSelect(el);
    });

    /**
     * Hide elements when clicked on document
     */
    document.addEventListener("click", function (e) {
        const target = e.target;
        const tagName = target.tagName;

        if (target.classList.contains("dropdown")) return false;

        if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
            return false;
        }

        if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
            return false;
        }

        document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
            el.classList.remove("selecting");
        });
    });

    /**
     * Switching between form steps
     */
    class FormSteps {
        constructor(form) {
            this.$form = form;
            this.$next = form.querySelectorAll(".next-step");
            this.$prev = form.querySelectorAll(".prev-step");
            this.$step = form.querySelector(".form--steps-counter span");
            this.currentStep = 1;

            this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
            const $stepForms = form.querySelectorAll("form > div");
            this.slides = [...this.$stepInstructions, ...$stepForms];

            this.init();
        }

        /**
         * Init all methods
         */
        init() {
            this.events();
            this.updateForm();
        }

        /**
         * All events that are happening in form
         */
        events() {
            // Next step
            this.$next.forEach((btn) => {
                btn.addEventListener("click", (e) => {
                    e.preventDefault();
                    this.currentStep++;
                    this.updateForm();
                });
            });

            // Previous step
            this.$prev.forEach((btn) => {
                btn.addEventListener("click", (e) => {
                    e.preventDefault();
                    this.currentStep--;
                    this.updateForm();
                });
            });

            // Form submit
            this.$form
                .querySelector("form")
                .addEventListener("submit", (e) => this.submit(e));
        }

        /**
         * Update form front-end
         * Show next or previous section etc.
         */
        updateForm() {
            console.log("Updated userData:", userData);
            this.$step.innerText = this.currentStep;

            // TODO: Validation
            userData.quantity = document.querySelector("input[name='quantity']").value;
            userData.street = document.querySelector("input[name='street']").value;
            userData.city = document.querySelector("input[name='city']").value;
            userData.zipCode = document.querySelector("input[name='zipCode']").value;
            userData.pickUpDate = document.querySelector("input[name='pickUpDate']").value;
            userData.pickUpTime = document.querySelector("input[name='pickUpTime']").value;
            userData.pickUpComment = document.querySelector("textarea[name='pickUpComment']").value;
            updateCategories();
            updateInstitution();

            this.slides.forEach((slide) => {
                slide.classList.remove("active");

                if (slide.dataset.step == this.currentStep) {
                    slide.classList.add("active");
                }
            });

            this.$stepInstructions[0].parentElement.parentElement.hidden =
                this.currentStep >= 5;
            this.$step.parentElement.hidden = this.currentStep >= 5;

            // TODO: get data from inputs and show them in summary

            console.log("Updated userData:", userData);
        }
    }

    function updateCategories() {
        userData.categories = [];

        categoryData.forEach(category => {
            const checkbox = document.querySelector(`.form-group--checkbox input[id='category${category.id}']`);

            if (checkbox && checkbox.checked) {
                userData.categories.push(category.name);
            }
        });
    }

    function updateInstitution() {

        const checkedRadio = document.querySelector("input[name='institution']:checked");

        if (checkedRadio) {
            const institutionId = parseInt(checkedRadio.value);

            // Find the institution object in institutionData based on the identifier
            const selectedInstitution = institutionData.find(institution => institution.id === institutionId);

            // Assign the institution name to userData.institution
            userData.institution = selectedInstitution ? selectedInstitution.name : null;
            showSummary(list1, list2);
        }
    }

    const form = document.querySelector(".form--steps");
    if (form !== null) {
        const formSteps = new FormSteps(form);
        form.querySelector(".btn.next-step:last-child").addEventListener("click", function () {
            showSummary(list1, list2);
        });
    }


    function showSummary(list1, list2) {
        const addressList = document.getElementById("addressList");
        const pickupList = document.getElementById("pickupList");

        list1.innerHTML = "";
        list2.innerHTML = "";
        addressList.innerHTML = "";
        pickupList.innerHTML = "";

        // Add elements to list1 and list2
        list1.appendChild(addSummaryItem(`${userData.quantity} ${userData.quantity === "1" ? "worek" : "worki"} ${userData.categories}`, true));
        list2.appendChild(addSummaryItem(`Dla fundacji "${userData.institution}"`, false));

        // Add data about the pickup address
        addListItem(addressList, userData.street);
        addListItem(addressList, userData.city);
        addListItem(addressList, userData.zipCode);

        // Add data about the pickup schedule
        addListItem(pickupList, userData.pickUpDate);
        addListItem(pickupList, userData.pickUpTime);
        addListItem(pickupList, userData.pickUpComment);
    }

    function addSummaryItem(content, isList1 = false) {
        const listItem = document.createElement("li");
        const iconClass = isList1 ? "icon-bag" : "icon-hand";

        listItem.innerHTML = `<span class="icon ${iconClass}"></span><span class="summary--text">${content}</span>`;
        return listItem;
    }

    function addListItem(list, content) {
        const listItem = document.createElement("li");
        listItem.textContent = content;
        list.appendChild(listItem);
    }

});
