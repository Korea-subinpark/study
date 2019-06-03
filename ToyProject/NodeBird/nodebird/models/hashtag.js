module.exports = (sequelize, DataTypes) => (
    sequelize.define('hashtag', {
        title: {
            type: DataTypes.STRING(15),
            allowNull: false,
            unique: true,
        },
    }, { //createAt, updateAt, deleteAt 컬럼 생성
        timestamps: true,
        paranoid: true,
    })
);