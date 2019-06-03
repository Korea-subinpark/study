module.exports = (sequelize, DataTypes) => (
    sequelize.define('hashtag', {
        title: {
            type: DataTypes.STRING(15),
            allowNull: false,
            unique: true,
        },
    }, {
        timestamps: true, //createAt, updateAt
        paranoid: true, //deleteAt
    })
);