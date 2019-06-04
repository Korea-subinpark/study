const local = require('./localStrategy');
const kakao = require('./kakaoStrategy');
const { User } = require('../models');

module.exports = (passport) => {
    passport.serializeUser((user, done) => { //serializeUser 함수는 req.session 객체에 어떤 데이터를 저장할지 선택한다
        done(null, user.id); //done 첫 번째 인자는 err 발생 시 사용, 사용자 정보를 모두 세션에 저장하면 용량이 커지고 데이터 일관성에 문제가 생기므로 id만 저장
    });

    passport.deserializeUser((user, done) => { //세션에 저장한 id를 통해 사용자 정보 객체를 불러온다
        User.find({ where: { id } })
            .then(user => done(null, user))
            .catch(err => done(err));
    });

    local(passport);
    kakao(passport);
};