exports.isLoggedIn = (req, res, next) => {
    if(req.isAuthenticated()) { //req.isAuthenticated() -> 로그인 중이면 true 반환, 아니면 false 반환하는 함수
        next();
    } else {
        res.status(403).send('로그인 필요');
    }
};

exports.isNotLoggedIn = (req, res, next) => {
    if(!req.isAuthenticated()) {
        next();
    } else {
        res.redirect('/');
    }
};