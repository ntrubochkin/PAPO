export const ToastModule = (() => {
    let toastModule = {};

    let offscreenRightOfset = 350;
    let minAliveTime = 1500; //in and out animations
    let halflife = minAliveTime = minAliveTime / 2;

    let removeTrigger = true;
    let removeTriggerCount = 5;
    let removeCount = 0;

    let initialized = false;
    let container = {};
    let styles = {};
    let toasts = [];

    let defaultMessage = "TOAST";
    let defaultCloseTime = 5000;
    let defaultOnClose = () => { };
    let defaultCanClose = false;

    function clamp(value, min, max) {
        return Math.min(Math.max(value, min), max);
    }

    //in function
    //https://stackoverflow.com/questions/5207301/jquery-easing-functions-without-using-a-plugin
    //t = current animation time
    //b = start coord
    //c = end coord
    //d = duration
    function easeOutBounce(x, t, b, c, d) {
        if ((t /= d) < (1 / 2.75)) {
            return c * (7.5625 * t * t) + b;
        } else if (t < (2 / 2.75)) {
            return c * (7.5625 * (t -= (1.5 / 2.75)) * t + .75) + b;
        } else if (t < (2.5 / 2.75)) {
            return c * (7.5625 * (t -= (2.25 / 2.75)) * t + .9375) + b;
        } else {
            return c * (7.5625 * (t -= (2.625 / 2.75)) * t + .984375) + b;
        }
    }

    //out function
    function easeOutQuint(x, t, b, c, d) {
        return c * ((t = t / d - 1) * t * t * t * t + 1) + b;
    }

    function animateToast(element,
                          startOffset,
                          duration,
                          mathFunction,
                          drawFunction,
                          stopCallback,
                          onAnimationEnd)
    {
        let start = performance.now();

        function loop(time) {
            let stop = stopCallback();
            if (stop) {
                onAnimationEnd();
                return;
            }

            let timePassed = time - start;
            let fraction = timePassed / duration;

            if (fraction > 1) {
                fraction = 1;
            }

            let nextX = mathFunction(0, timePassed, startOffset, offscreenRightOfset, duration);
            drawFunction(element, nextX, timePassed);

            if (fraction < 1) {
                requestAnimationFrame(loop);
            } else {
                onAnimationEnd();
            }
        }

        requestAnimationFrame(loop);
    }

    function removeToasts() {
        removeTrigger = false;
        toasts.forEach((t) => {
            t.remove(false);
        });
        toasts = [];
        removeTrigger = true;
    }

    function clearToasts() {
        toasts = toasts.filter(t => t.isAlive());
        removeCount = 0;
    }

    function appendToast(message = defaultMessage,
                         time = defaultCloseTime,
                         onClose = defaultOnClose,
                         canClose = defaultCanClose)
    {
        if (!initialized) {
            return {
                "toast": "burh"
            };
        }

        if (message == null || message === "") {
            message = defaultMessage;
        }
        if (time < minAliveTime) {
            time = minAliveTime;
        }

        let alive = true;
        let offsetFromLeftSide = 0;
        let stopOutAnimation = false;
        let toastObj = {};
        let toast = document.createElement("div");
        let timeout;

        function realRemove() {
            container.removeChild(toast);
            toast = {};
            if (onClose != null) {
                onClose();
            }
            if ((++removeCount >= removeTriggerCount) && removeTrigger) {
                clearToasts();
            }
        }

        function remove(animate = true) {
            if (alive) {
                alive = false;
                clearTimeout(timeout);
                if(animate) {
                    animateToast(toast,
                                 offsetFromLeftSide,
                                 halflife,
                                 easeOutQuint,
                                 drawOut,
                                 stopOutAnimationCallback,
                                 realRemove);
                } else {
                    realRemove();
                }
                return;
            }
            if(!stopOutAnimation) {
                stopOutAnimation = true;
            }
        }

        function isAlive() {
            return alive;
        }

        function drawIn(e, x, t) {
            let result = clamp(offscreenRightOfset - x, 0, offscreenRightOfset);
            offsetFromLeftSide = result;
            e.style.transform = `translateX(+${result}px)`;
        }

        function stopInAnimationCallback() {
            return !alive;
        }

        function drawOut(e, x, t) {
            let result = clamp(x, 0, offscreenRightOfset);
            offsetFromLeftSide = result;
            if(result >= offscreenRightOfset) {
                stopOutAnimation = true;
            }
            e.style.transform = `translateX(+${result}px)`;
        }

        function stopOutAnimationCallback() {
            return stopOutAnimation;
        }

        if (canClose) {
            toast.addEventListener("click", remove);
        }
        timeout = setTimeout(remove, time);

        toast.classList.add("toast-general");
        toast.classList.add("toast");
        toast.textContent = message;

        toastObj.remove = remove;
        toastObj.isAlive = isAlive;

        container.appendChild(toast);
        animateToast(toast,
                     offsetFromLeftSide,
                     halflife,
                     easeOutBounce,
                     drawIn,
                     stopInAnimationCallback,
                     () => {});
        toasts.push(toastObj);

        return toastObj;
    }

    function initializeModule(head, body) {
        if (initialized) {
            return {
                "toast_module_creation_result": "bruh"
            };
        }
        if (head.tagName !== "HEAD") {
            return;
        }
        if (body.tagName !== "BODY") {
            return;
        }

        function appendStyle(styleElement, styleString) {
            styleElement.textContent += styleString;
        }

        styles = document.createElement("style");
        container = document.createElement("div");

        appendStyle(styles, `
            .toast-general {
                padding: 0;
                margin: 0;
                box-sizing: border-box;
            }
        `);
        appendStyle(styles, `
            .toast-container {
                position: absolute;
                height: 90vh;
                width: 300px;
                right: 0;
                bottom: 0;
                top: 0;
                margin-top: auto;
                margin-bottom: auto;
                padding: 0.25rem;
                display: flex;
                flex-direction: column;
                gap: .5rem;
                overflow-y: hidden;
                overflow-x: hidden;
            }
        `);
        appendStyle(styles, `
            .toast {
                padding: 1rem;
                border-radius: .25rem;
                background-color: #16a084b9;
                outline: 1px solid rgb(41, 214, 171);
                color: white;
                max-width: 250px;
                cursor: pointer;
                word-wrap: break-word;
                transform: translateX(+350px);
            }
        `);

        container.classList.add("toast-general");
        container.classList.add("toast-container");

        head.append(styles);
        body.appendChild(container);

        initialized = true;
    }

    function destroyModule() {
        if (!initialized) {
            return {
                "toast_module_deletion_result": "bruh"
            };
        }

        removeToasts();

        container.parentElement.removeChild(container);
        styles.parentElement.removeChild(styles);

        initialized = false;
        container = {};
        styles = {};
    }

    toastModule.buyToaster = initializeModule;
    toastModule.makeToast = appendToast;
    toastModule.removeExpiredToasts = clearToasts;
    toastModule.throwOutToasts = removeToasts;
    toastModule.throwAwayToaster = destroyModule;

    return toastModule;
})();