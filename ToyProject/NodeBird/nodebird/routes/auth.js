const express = require('express');
const passport = require('passport');
const bcrypt = require('bcrypt');
const { isLoggedIn, isNotLoggedIn } = require('./middlewares');
const { User } = require('../models');

const router = express.Router();
router.post('/join', isNotLoggedIn, async (req, res, next) => {
    const { email, nick, password } = req.body;
    try {
        const exUser = await User.find({ where: { email } });
        if(exUser) {
            req.flash('joinError', '이미 가입된 이메일입니다.');
            return res.redirect('/join');
        }
        const hash = await bcrypt.hash(password, 12); //두 번째 인자 값이 커질수록 비밀번호를 알아내기 어려워지지만 암호화 시간도 오래 걸린다(31까지 사용가능)
        await User.create({
            email,
            nick,
            password: hash,
        });
        return res.redirect('/');
    } catch (error) {
        console.error(error);
        return next(error);
    }
});
router.post('/login', isNotLoggedIn, (req, res, next) => {
    passport.authenticate('local', (authError, user, info) => {
        if(authError) { //실패했을 경우
            console.error(authError);
            return next(authError);
        }
        if(!user) {
            req.flash('loginError', info.message);
            return res.redirect('/');
        }
        return req.login(user, (loginError) => {
            if(loginError) {
                console.log(loginError);
                return next(loginError);
            }
            return res.redirect('/');
        });
    })(req, res, next); //미들웨어 내의 미들웨어에는 (req, res, next)를 붙인다
});
router.get('/logout', isLoggedIn, (req, res) => {
    req.logout(); //req.user 객체 제거
    req.session.destroy(); //req.session 객체의 내용 제거
    res.redirect('/'); //메인 페이지로 이동
});
module.exports = router;