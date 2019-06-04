const LocalStrategy = require('passport-local').Strategy;
const bcrypt = require('bcrypt');

const { User } = require('../models');

module.exports = (passport) => {
    passport.use(new LocalStrategy({
        usernameField: 'email',
        passwordField: 'password',
    }, async (email, password, done) => {
        try {
            const exUser = await User.findOne({ where: { email } });
            if(exUser) { //이메일이 존재하는 경우
                const result = await bcrypt.compare(password, exUser.password); //비밀번호 비교
                if(result) { //비밀번호 일치
                    done(null, exUser);
                } else { //비밀번호 불일치
                    done(null, false, { message: '비밀번호가 일치하지 않습니다.' });
                }
            } else {
                done(null, false, {message: '가입되지 않은 회원입니다.' });
            }
        } catch(error) {
            console.error(error);
            done(error);
        }
    }));
};